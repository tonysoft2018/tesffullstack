export interface GenericResponse<D> {
    status: boolean;
    message: string;
    data: D;
}
