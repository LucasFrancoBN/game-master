export interface IPagination<T> {
  content: T[];
  currentPage: number;
  pageSize: number;
  totalElements: number;
}
