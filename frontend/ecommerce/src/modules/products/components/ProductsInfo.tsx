"use client";

import FilterSidebar from "@/src/common/components/catalogue/FilterSidebar";
import ProductGrid from "@/src/common/components/catalogue/ProductGrid";
import ProductPreview from "@/src/common/components/catalogue/ProductPreview";
import { Product } from "@/src/common/types/products";
import { useEffect, useState } from "react";
import { fetchAllItems } from "@/src/common/api/itemsApiService";
import { ItemSearchRequest } from "@/src/common/types/search";
import { searchItems } from "@/src/common/api/searchService";


const ProductInfo = () => {
  const [selectedProduct, setSelectedProduct] = useState<Product | null>(null);

  // fetching all the items from the api
  const [products, setProducts] = useState<Product[]>([]); // storing the items in the product type format

  const [loading, setLoading] = useState(false);
  const [totalPages, setTotalPages] = useState(0); // setting the total pages for the pagination
  const [totalElements, setTotalElements] = useState(0); // setting the total elements per page for pagination

  const [filters, setFilters] = useState<ItemSearchRequest>({
    categories: [],
    sellerIds: [],

    minPrice: undefined,
    maxPrice: undefined,

    searchText: "",

    sortBy: "NAME",

    page: 0,
    size: 12,
  });

  /**
   * Fetch products whenever filters change.
   */
  useEffect(() => {
    loadProducts();
  }, [filters]);

  /**
   * Loads the catalogue from the backend.
   */
  const loadProducts = async () => {
    try {
      setLoading(true);
      const page = await searchItems(filters);

      setProducts(page.content);  // setting the products informations
      setTotalPages(page.totalPages);  // setting the total number of pages
      setTotalElements(page.totalElements);  // setting the total number of elements returned
    } catch (error) {
      console.error("Error fetching products:", error);
    } finally {
      setLoading(false);
    }
  };

  /*useEffect(() => {
    const loadProducts = async () => {
        try {
            const response = await fetchAllItems();  // calling the api to get the details of the items
            console.log("Items: ",response); // getting the items response on the console
            setItems(response.data);  // setting the response in the items 
        } catch (error) {
            console.error(error);
        }
    };
    loadProducts();
  }, []); */

  return (
    <main className="min-h-screen bg-background text-foreground transition-colors">
      <div className="max-w-[1900px] mx-auto p-6">
        <div
          className={
            selectedProduct
              ? "grid grid-cols-[280px_minmax(0,1fr)_500px] gap-6"
              : "grid grid-cols-[280px_minmax(0,1fr)] gap-6"
          }
        >
          {/* filters */}

          <FilterSidebar filters={filters}
            onChange={(updatedFilters) => {
              setFilters({
                ...updatedFilters,
                page: 0,
              });
            }}/>

          {/* catalogue */}

          <ProductGrid
            products={products}
            loading={loading}
            totalElements={totalElements}
            currentPage={filters.page}
            totalPages={totalPages}
            onSelect={setSelectedProduct}
            previewOpen={selectedProduct !== null}
            onPageChange={(page) =>
              setFilters({
                ...filters,
                page,
              })
            }
          />

          {/* preview */}

          {selectedProduct && <ProductPreview product={selectedProduct} onClose={() => setSelectedProduct(null)}/>}
        </div>
      </div>
    </main>
  );
};

export default ProductInfo;
