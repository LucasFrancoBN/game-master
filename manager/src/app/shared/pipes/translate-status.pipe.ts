import { Pipe, PipeTransform } from '@angular/core';
import { ProductStatus } from '../../features/product/models/product-status.enum';
import { translateStatus } from '../../features/product/utils/product-status.utils';

@Pipe({
  name: 'translateStatus',
})
export class TranslateStatusPipe implements PipeTransform {
  transform(status: ProductStatus): unknown {
    return translateStatus(status);
  }
}
