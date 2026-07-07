interface PageHeadingProps {
  title: string;
  description?: string;
}

const PageHeading = ({ title, description }: PageHeadingProps) => {
  return (
    <header className="mb-8 border-b border-neutral-200 pb-5 dark:border-neutral-800">
      <div className="space-y-2">
        <h1 className="font-sora text-[28px] font-semibold tracking-[-0.02em] text-neutral-950 dark:text-white">
          {title}
        </h1>

        {description && (
          <p className="max-w-3xl text-[15px] leading-6 text-neutral-500 dark:text-neutral-400">
            {description}
          </p>
        )}
      </div>
    </header>
  );
};

export default PageHeading;