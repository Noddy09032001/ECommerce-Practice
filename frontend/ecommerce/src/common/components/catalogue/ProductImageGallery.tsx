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

      <div className="h-96 rounded-2xl bg-zinc-100 dark:bg-zinc-900 border border-zinc-200 dark:border-zinc-800 flex items-center justify-center">
        <img src={selectedImage?.url} className="max-h-80 object-contain" />
      </div>

      {/* thumbnails */}

      <div className="mt-5 flex gap-3 overflow-x-auto">
        {images.map((image) => (
          <button
            key={image.id}
            onClick={() => setSelectedImage(image)}
            className={`h-20 w-20 rounded-xl border overflow-hidden flex-shrink-0 ${
              selectedImage?.id === image.id
                ? "border-black dark:border-white"
                : "border-zinc-200 dark:border-zinc-800"
            }`}
          >
            <img src={image.url} className="h-full w-full object-cover" />
          </button>
        ))}
      </div>
    </div>
  );
}
