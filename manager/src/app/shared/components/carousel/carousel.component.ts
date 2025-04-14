import {
  AfterViewInit,
  Component,
  ElementRef,
  inject,
  OnDestroy,
  Renderer2,
  ViewChild,
} from '@angular/core';
import { debounceTime, fromEvent, Subject, takeUntil } from 'rxjs';
import { ButtonComponent } from '../button/button.component';

interface Slide {
  position: number;
  element: HTMLDivElement;
}

interface SlideIndices {
  prev: number | undefined;
  active: number;
  next: number | undefined;
}

const DEBOUNCE_RESIZE_TIME = 200;
const MOVEMENT_THRESHOLD = 120;
const TRANSITION_TIME = 'transform .3s';

@Component({
  selector: 'app-carousel',
  imports: [ButtonComponent],
  templateUrl: './carousel.component.html',
})
export class CarouselComponent implements AfterViewInit, OnDestroy {
  private readonly renderer = inject(Renderer2);
  private readonly destroy$ = new Subject<void>();

  private controlListeners: (() => void)[] = [];
  private mousedownListener = () => {};
  private mouseupListener = () => {};
  private moveListener = () => {};

  private isDragging = false;
  private slides: Slide[] = [];
  private dragState = {
    finalPosition: 0,
    startX: 0,
    movement: 0,
    movePosition: 0,
  };
  private currentIndices: SlideIndices = {
    prev: 0,
    active: 0,
    next: 1,
  };

  @ViewChild('wrapper') wrapper!: ElementRef<HTMLDivElement>;
  @ViewChild('slide') slide!: ElementRef<HTMLDivElement>;
  @ViewChild('wrapperControl') wrapperControl!: ElementRef<HTMLDivElement>;
  controls!: HTMLCollection;
  contentSlides!: HTMLCollection;

  ngAfterViewInit(): void {
    setTimeout(() => {
      this.initializeCarousel();
    });
  }

  ngOnDestroy(): void {
    this.controlListeners.forEach((removeListener) => removeListener());
    this.mousedownListener();
    this.mouseupListener();
    this.destroy$.next();
    this.destroy$.complete();
  }

  private initializeCarousel(): void {
    this.contentSlides = this.slide.nativeElement.children;
    this.controls = this.wrapperControl.nativeElement.children;

    this.transition(true);
    this.setupSlides();
    this.setupEventListeners();
    this.updateActiveSlide(0);
  }

  private setupEventListeners(): void {
    this.setupControlNavigation();
    this.setupDragEvents();
    this.setupResizeHandler();
  }

  private setupControlNavigation(): void {
    Array.from(this.controls).forEach((el, i) => {
      const element = el as HTMLElement;
      this.addControlEvent(element, i);
    });
  }

  private addControlEvent(item: HTMLElement, index: number) {
    const controlEvent = this.renderer.listen(
      item,
      'click',
      (event: MouseEvent) => {
        event.preventDefault();
        this.updateActiveSlide(index);
      }
    );
    this.controlListeners.push(controlEvent);
  }

  private setupDragEvents() {
    const { nativeElement } = this.wrapper;

    this.mousedownListener = this.renderer.listen(
      nativeElement,
      'mousedown',
      this.startDrag.bind(this)
    );
    this.mouseupListener = this.renderer.listen(
      document,
      'mouseup',
      this.endDrag.bind(this)
    );
  }

  private setupResizeHandler() {
    fromEvent(window, 'resize')
      .pipe(debounceTime(DEBOUNCE_RESIZE_TIME), takeUntil(this.destroy$))
      .subscribe(() => {
        this.setupSlides();
        this.updateActiveSlide(this.currentIndices.active);
      });
  }

  private setupSlides() {
    this.slides = [...this.contentSlides].map((el) => {
      const position = this.slidePosition(el as HTMLDivElement);
      return { position, element: el as HTMLDivElement };
    });
  }

  private transition(active: boolean) {
    this.slide.nativeElement.style.transition = active ? TRANSITION_TIME : '';
    this.wrapperControl.nativeElement.style.transition = active
      ? TRANSITION_TIME
      : '';
  }

  private updateSlidePosition(distX: number) {
    this.dragState.movePosition = distX;
    this.slide.nativeElement.style.transform = `translate3d(${distX}px, 0, 0)`;
  }

  private calculateSlidePosition(clientX: number) {
    this.dragState.movement = (this.dragState.startX - clientX) * 1.6;
    return this.dragState.finalPosition - this.dragState.movement;
  }

  private startDrag(event: MouseEvent) {
    event.preventDefault();
    this.isDragging = false;
    this.dragState.startX = event.clientX;
    this.moveListener = this.renderer.listen(
      this.wrapper.nativeElement,
      'mousemove',
      this.handleDragMove.bind(this)
    );
    this.transition(false);
  }

  private handleDragMove(event: MouseEvent) {
    this.isDragging = true;
    const pointerPosition = event.clientX;
    const finalPosition = this.calculateSlidePosition(pointerPosition);
    this.updateSlidePosition(finalPosition);
  }

  private endDrag() {
    // Limpando Listener
    this.moveListener();

    if (!this.isDragging) return;

    this.dragState.finalPosition = this.dragState.movePosition;
    this.transition(true);
    this.handleSlideChangeAfterDrag();

    this.isDragging = false;
  }

  private handleSlideChangeAfterDrag() {
    if (
      this.dragState.movement > MOVEMENT_THRESHOLD &&
      this.currentIndices.next !== undefined
    )
      this.nextSlide();
    else if (
      this.dragState.movement < MOVEMENT_THRESHOLD &&
      this.currentIndices.prev !== undefined
    )
      this.previousSlide();
    else this.updateActiveSlide(this.currentIndices.active);
  }

  private updateActiveSlide(index: number) {
    const activeSlide = this.slides[index];

    this.updateSlideVisuals(index);
    this.transition(true);
    this.updateSlidePosition(activeSlide.position);
    this.updateSlideIndices(index);
    this.dragState.finalPosition = activeSlide.position;
  }

  private slidePosition(slide: HTMLDivElement) {
    const margin =
      (this.wrapper.nativeElement.offsetWidth - slide.offsetWidth) / 2;
    return -(slide.offsetLeft - margin);
  }

  private updateSlideIndices(index: number) {
    const last = this.slides.length - 1;
    this.currentIndices = {
      prev: index ? index - 1 : undefined,
      active: index,
      next: index === last ? undefined : index + 1,
    };
  }

  private updateSlideVisuals(activeIndex: number): void {
    // Atualiza slides
    this.slides.forEach((slide, index) => {
      slide.element.style.opacity = index === activeIndex ? '100%' : '50%';
    });

    // Atualiza controles
    Array.from(this.controls).forEach((control, index) => {
      const el = control as HTMLElement;
      el.style.opacity = index === activeIndex ? '100%' : '50%';
      el.style.scale = index === activeIndex ? '1.2' : '1';
    });
  }

  previousSlide() {
    if (this.currentIndices.prev !== undefined)
      this.updateActiveSlide(this.currentIndices.prev);
  }

  nextSlide() {
    if (this.currentIndices.next !== undefined)
      this.updateActiveSlide(this.currentIndices.next);
  }
}
