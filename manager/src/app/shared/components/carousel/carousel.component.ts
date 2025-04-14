import {
  AfterViewInit,
  Component,
  ElementRef,
  inject,
  Renderer2,
  ViewChild,
} from '@angular/core';
import { debounceTime, fromEvent } from 'rxjs';
import { ButtonComponent } from '../button/button.component';

interface ISlideArray {
  position: number;
  element: HTMLDivElement;
}

interface IIndex {
  prev: number | undefined;
  active: number;
  next: number | undefined;
}

@Component({
  selector: 'app-carousel',
  imports: [ButtonComponent],
  templateUrl: './carousel.component.html',
})
export class CarouselComponent implements AfterViewInit {
  private readonly renderer = inject(Renderer2);
  private moveListener = () => {};
  private hasMoved = false;
  private slideArray: ISlideArray[] = [];
  private dist = { finalPosition: 0, startX: 0, movement: 0, movePosition: 0 };
  private index: IIndex = {
    prev: 0,
    active: 0,
    next: 1,
  };
  @ViewChild('wrapper') wrapper!: ElementRef<HTMLDivElement>;
  @ViewChild('slide') slide!: ElementRef<HTMLDivElement>;
  @ViewChild('wrapperControl') wrapperControl!: ElementRef<HTMLDivElement>;
  control!: HTMLCollection;
  content!: HTMLCollection;

  ngAfterViewInit(): void {
    setTimeout(() => {
      this.content = this.slide.nativeElement.children;
      this.control = this.wrapperControl.nativeElement.children;
      this.bindEvents();
      this.transition(true);
      this.slidesConfig();
      this.changeSlide(0);
      this.addResizeEvent();
    });
  }

  private transition(active: boolean) {
    this.slide.nativeElement.style.transition = active ? 'transform .3s' : '';
    this.wrapperControl.nativeElement.style.transition = active
      ? 'transform .3s'
      : '';
  }

  private moveSlide(distX: number) {
    this.dist.movePosition = distX;
    this.slide.nativeElement.style.transform = `translate3d(${distX}px, 0, 0)`;
  }

  private updatePosition(clientX: number) {
    this.dist.movement = (this.dist.startX - clientX) * 1.6;
    return this.dist.finalPosition - this.dist.movement;
  }

  private onStart(event: MouseEvent) {
    event.preventDefault();
    this.hasMoved = false;
    this.dist.startX = event.clientX;
    this.moveListener = this.renderer.listen(
      this.wrapper.nativeElement,
      'mousemove',
      this.onMove.bind(this)
    );
    this.transition(false);
  }

  private onMove(event: MouseEvent) {
    this.hasMoved = true;
    const pointerPosition = event.clientX;
    const finalPosition = this.updatePosition(pointerPosition);
    this.moveSlide(finalPosition);
  }

  private onEnd() {
    // Limpando Listener
    this.moveListener();

    if (this.hasMoved) {
      this.dist.finalPosition = this.dist.movePosition;
      this.transition(true);
      this.changeSlideOnEnd();
    }

    this.hasMoved = false;
  }

  private changeSlideOnEnd() {
    if (this.dist.movement > 120 && this.index.next !== undefined)
      this.activeNextSlide();
    else if (this.dist.movement < 120 && this.index.prev !== undefined)
      this.activePrevSlide();
    else this.changeSlide(this.index.active);
  }

  private bindEvents() {
    Array.from(this.control).forEach((el, i) => {
      const element = el as HTMLElement;
      this.controlNav(element, i);
    });
    this.renderer.listen(
      this.wrapper.nativeElement,
      'mousedown',
      this.onStart.bind(this)
    );
    this.renderer.listen(document, 'mouseup', this.onEnd.bind(this));
  }

  private slidePosition(slide: HTMLDivElement) {
    const margin =
      (this.wrapper.nativeElement.offsetWidth - slide.offsetWidth) / 2;
    return -(slide.offsetLeft - margin);
  }

  private slidesConfig() {
    this.slideArray = [...this.content].map((el) => {
      const position = this.slidePosition(el as HTMLDivElement);
      return { position, element: el as HTMLDivElement };
    });
  }

  private slidesIndexNav(index: number) {
    const last = this.slideArray.length - 1;
    this.index = {
      prev: index ? index - 1 : undefined,
      active: index,
      next: index === last ? undefined : index + 1,
    };
  }

  private changeSlide(index: number) {
    const activeSlide = this.slideArray[index];
    this.slideArray.forEach((slide) => (slide.element.style.opacity = '50%'));
    Array.from(this.control).forEach((c) => {
      const el = c as HTMLElement;
      el.style.opacity = '50%';
      el.style.scale = '1';
    });
    activeSlide.element.style.opacity = '100%';
    (this.control[index] as HTMLElement).style.opacity = '100%';
    (this.control[index] as HTMLElement).style.scale = '1.2';
    this.transition(true);
    this.moveSlide(activeSlide.position);
    this.slidesIndexNav(index);
    this.dist.finalPosition = activeSlide.position;
  }

  private addResizeEvent() {
    fromEvent(window, 'resize')
      .pipe(debounceTime(200))
      .subscribe(() => {
        this.slidesConfig();
        this.changeSlide(this.index.active);
      });
  }

  activePrevSlide() {
    if (this.index.prev !== undefined) this.changeSlide(this.index.prev);
  }

  activeNextSlide() {
    if (this.index.next !== undefined) this.changeSlide(this.index.next);
  }

  controlNav(item: HTMLElement, index: number) {
    this.renderer.listen(item, 'click', (event: MouseEvent) => {
      event.preventDefault();
      this.changeSlide(index);
    });
  }
}
