"use client";

import { useState } from "react";
import { ProductImage } from "@/src/common/types/products";

interface Props {
  images: ProductImage[];
}

export default function ProductImageGallery({ images }: Props) {
  const [selectedImage, setSelectedImage] = useState(images?.[0]);

  return (
    <div>
      {/* main image */}

      <div className="h-96 rounded-2xl bg-secondary-bg border border-default flex items-center justify-center transition-colors">
        <img src={selectedImage?.url} className="max-h-80 object-contain" />
      </div>

      {/* thumbnails */}

      <div className="mt-5 flex gap-3 overflow-x-auto">
        {images.map((image) => (
          <button
            key={image.id}
            onClick={() => setSelectedImage(image)}
            className={`h-20 w-20 rounded-xl overflow-hidden flex-shrink-0 transition-colors ${
              selectedImage?.id === image.id
                ? "border-2 border-primary"
                : "border border-default"
            }`}
          >
            <img src={image.url} className="h-full w-full object-cover" />
          </button>
        ))}
      </div>
    </div>
  );
}
