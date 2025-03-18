import {ProductStatus} from "../core/models/product/product-status.enum";

const statusTranslations: Record<ProductStatus, string> = {
    [ProductStatus.AVAILABLE]: "Ativo",
    [ProductStatus.DISCOUNTED]: "Descontinuado",
    [ProductStatus.OUT_OF_STOCK]: "Fora de estoque"
}
