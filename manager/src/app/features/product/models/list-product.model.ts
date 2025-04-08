import { ProductStatus } from './product-status.enum';

export interface IListProduct {
  id: string;
  name: string;
  amount: string;
  price: string;
  weight: string;
  status: ProductStatus;
}
