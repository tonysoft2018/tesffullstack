export interface ApiResponse<T> {
  status: boolean;
  msg: string;
  data: T[] | null;
}
