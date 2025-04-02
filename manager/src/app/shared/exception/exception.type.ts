interface IFieldError {
    field: string,
    message: string
}

export interface IException {
    timestamp: string,
    status: number,
    message: string,
    path: string,
    errors?: IFieldError[];
}

export function isIException(error: any): error is IException {
    return typeof error === "object" &&
        error!== null &&
        "timestamp" in error && 
        "status" in error &&
        "message" in error &&
        "path" in error;
}
