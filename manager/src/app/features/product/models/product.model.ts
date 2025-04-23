import { ImageType } from './image-type.enum';
import { ProductStatus } from './product-status.enum';

export interface IProductModel {
  id: string;
  name: string;
  description: string;
  amount: number;
  price: number;
  weight: number;
  status: ProductStatus;
  images: IProductImage[];
}

export interface IProductImage {
  name: string;
  url: string;
  path: string;
  type: ImageType;
  size: number;
  index: number;
}
