"use client";

interface Props {
  currentPage: number;
  totalPages: number;
  onPageChange: (page: number) => void;
}

export default function OrdersPagination({currentPage, totalPages, onPageChange,}: Props) {
  return (
    <div className="mt-8 flex justify-between items-center">
      <p className="text-sm text-secondary">
        Page {currentPage} of {totalPages}
      </p>

      <div className="flex gap-2">
        <button
          disabled={currentPage === 1}
          onClick={() => onPageChange(currentPage - 1)}
          className="
            px-4
            py-2
            rounded-xl
            border
            border-default
            bg-card
            hover:bg-secondary-bg
            disabled:opacity-50
            disabled:cursor-not-allowed
            transition-colors
          "
        >
          Previous
        </button>

        {Array.from({ length: totalPages }).map((_, index) => (
          <button
            key={index}
            onClick={() => onPageChange(index + 1)}
            className={`
              h-10
              w-10
              rounded-xl
              transition-colors
              ${
                currentPage === index + 1
                  ? "bg-primary text-white"
                  : "bg-card border border-default hover:bg-secondary-bg"
              }
            `}
          >
            {index + 1}
          </button>
        ))}

        <button
          disabled={currentPage === totalPages}
          onClick={() => onPageChange(currentPage + 1)}
          className="
            px-4
            py-2
            rounded-xl
            border
            border-default
            bg-card
            hover:bg-secondary-bg
            disabled:opacity-50
            disabled:cursor-not-allowed
            transition-colors
          "
        >
          Next
        </button>
      </div>
    </div>
  );
}
