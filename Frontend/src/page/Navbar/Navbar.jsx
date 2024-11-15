import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { Button } from "@/components/ui/button";
import {
  Sheet,
  SheetContent,
  SheetHeader,
  SheetTitle,
  SheetTrigger,
} from "@/components/ui/sheet";
import {
  DragHandleHorizontalIcon,
  MagnifyingGlassIcon,
} from "@radix-ui/react-icons";
import SideBar from "./SideBar";
import { useSelector } from "react-redux";
const Navbar = () => {
    const { auth } = useSelector((store)=>store);
  return (
    <div className="px-2 py-3 border-b z-50 bg-background bg-opacity-0 sticky top-0 left-0 right-0 flex justify-between items-center">
      <div className="flex items-center gap-3">
        <Sheet>
          <SheetTrigger>
            <Button
              variant="ghost"
              size="icon"
              className="rounded-full h-11 w-11"
            >
              <DragHandleHorizontalIcon />
            </Button>
          </SheetTrigger>
          <SheetContent
            className="w-72 border-r-0 flex flex-col justify-center"
            side="left"
          >
            <SheetHeader>
              <SheetTitle>
                <div className="text-3xl flex justify-center items-center gap-1">
                  <Avatar>
                    <AvatarImage src="https://i.etsystatic.com/7749274/r/il/56f306/4347715633/il_fullxfull.4347715633_dyne.jpg"></AvatarImage>
                  </Avatar>
                  <div>
                    <span className="font-bold text-red-600">McQueen</span>
                    <span> Cars</span>
                  </div>
                </div>
              </SheetTitle>
            </SheetHeader>
            <SideBar></SideBar>
          </SheetContent>
        </Sheet>
        <p className="text-sm lg:text-base cursor-pointer">
          {" "}
          <span className="font-bold text-red-600">McQueen</span>
          <span> Cars</span>
        </p>
        <div className="p-0 ml-9 flex items-center">
          {" "}
          {}
          <input
            type="text"
            placeholder="Enter Keyword"
            value={null}
            onChange={null} 
            className="border border-gray-400 rounded p-1 text-white bg-black" 
          />
          <Button
            variant="outline"
            className="flex items-center gap-3 ml-2"
            onClick={null}
          >
            {" "}
            {}
            <MagnifyingGlassIcon />
            <span>Search</span>
          </Button>
        </div>
      </div>
      <div className="relative group">
        <Avatar>
        <AvatarFallback className="flex items-center justify-center w-full h-full text-xl font-bold text-white bg-gradient-to-r from-blue-500 to-green-500 rounded-full border-2 border-white shadow-md">
  {auth.user?.fullName[0].toUpperCase()}
</AvatarFallback>
        </Avatar>
        <span className="absolute left-1/2 transform -translate-x-1/2 top-full mt-2 w-max opacity-0 group-hover:opacity-100 transition-opacity duration-300 bg-black text-white text-sm rounded px-2 py-1">
            {auth.user?.fullName}
          </span>
      </div>
    </div>
  );
};

export default Navbar;