import { useEffect, useState } from 'react';

function useWindowSize() {
  const [windowSize, setWindowSize] = useState({
    width: typeof window !== "undefined" ? window.innerWidth : 0,
    height: typeof window !== "undefined" ? window.innerHeight : 0,
  });

  useEffect(() => {
    function handleResize() {
      setWindowSize({
        width: window.innerWidth,
        height: window.innerHeight,
      });
    }

    // Set initial size
    handleResize();

    window.addEventListener("resize", handleResize);

    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, []);

  return windowSize;
}


const useIsMobile = () => {
  const { width } = useWindowSize();
  const [isMobile, setIsMobile] = useState(width < 769);

  useEffect(() => {
    setIsMobile(width < 821);
  }, [width]);

  return isMobile;
};

export default useIsMobile;