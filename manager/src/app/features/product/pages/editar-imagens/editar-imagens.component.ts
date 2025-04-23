import { Component, inject, OnInit } from '@angular/core';
import {
  CdkDropList,
  CdkDrag,
  CdkDragDrop,
  moveItemInArray,
} from '@angular/cdk/drag-drop';
import { IProductImage } from '../../models/product.model';
import { ActivatedRoute } from '@angular/router';
import { GetProductByIdService } from '../../service/get-product-by-id.service';
import { finalize } from 'rxjs';
import { IException } from '../../../../shared/exception/exception.type';
import { NzAlertComponent } from 'ng-zorro-antd/alert';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { ButtonComponent } from '../../../../shared/components/button/button.component';
import { UpdateImagesService } from '../../service/update-images.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { LoadingComponent } from '../../../../shared/components/loading/loading.component';

@Component({
  selector: 'app-editar-imagens',
  imports: [
    CdkDropList,
    CdkDrag,
    NzAlertComponent,
    NzIconModule,
    ButtonComponent,
    LoadingComponent,
  ],
  templateUrl: './editar-imagens.component.html',
})
export class EditarImagensComponent implements OnInit {
  private readonly message = inject(NzMessageService);
  private readonly route = inject(ActivatedRoute);
  private readonly getProductById = inject(GetProductByIdService);
  private readonly updateImages = inject(UpdateImagesService);

  loading: boolean = true;
  error?: string;
  imageList?: IProductImage[];

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.getProductById
      .getProduct(id)
      .pipe(finalize(() => (this.loading = false)))
      .subscribe({
        next: (data) => {
          const orderedImages = data.images.sort((a, b) => a.index - b.index);
          this.imageList = orderedImages;
        },
        error: (e: IException) => (this.error = e.message),
      });
  }

  edit() {
    if (!this.imageList) return;

    this.loading = true;

    this.updateImages
      .update(this.imageList)
      .pipe(finalize(() => (this.loading = false)))
      .subscribe({
        next: () => this.message.success('Imagens editadas com sucesso.'),
        error: () => this.message.error('Não foi possível editar as imagens'),
      });
  }

  drop(event: CdkDragDrop<IProductImage[]>) {
    if (!this.imageList) return;
    moveItemInArray(this.imageList, event.previousIndex, event.currentIndex);
    this.imageList.forEach((image, index) => (image.index = index));
  }
}
