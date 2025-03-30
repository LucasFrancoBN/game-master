import {ProductStatus} from "../models/product-status.enum";

export const statusTranslations: Record<ProductStatus, string> = Object.freeze({
    [ProductStatus.AVAILABLE]: "Ativo",
    [ProductStatus.UNAVAILABLE]: "Inativo",
    [ProductStatus.DISCOUNTED]: "Descontinuado",
    [ProductStatus.OUT_OF_STOCK]: "Fora de estoque"
});

export const statusTranslationsReverse: Record<string, ProductStatus> = Object.freeze({
    'Ativo': ProductStatus.AVAILABLE,
    'Inativo': ProductStatus.UNAVAILABLE,
    'Descontinuado': ProductStatus.DISCOUNTED,
    'Fora de estoque': ProductStatus.OUT_OF_STOCK
});

export function translateStatus(status: ProductStatus) {
    return statusTranslations[status] || status;
}

export function convertStatusToEnglish(ptStatus: ProductStatus) {
    return statusTranslationsReverse[ptStatus] || null;
}
