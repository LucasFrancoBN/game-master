import { ProductStatus } from './product-status.enum';

export interface IRegisterProduct {
  name: string;
  description: string;
  price: number;
  weight: number;
  status: ProductStatus;
  amount: number;
}
