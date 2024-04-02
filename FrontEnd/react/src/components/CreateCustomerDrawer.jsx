import {  Button,
Drawer,
DrawerBody,
DrawerCloseButton,
DrawerFooter,
DrawerHeader,
DrawerOverlay,useDisclosure,DrawerContent} from "@chakra-ui/react";
import CreateCustomerForm from "./CreateCustomerForm";
const AddIcon =()=>"+";
const CloseIcon =()=>"x";

const DrawerForm = ({fetchCustomers})=>{
    const { isOpen, onOpen, onClose } = useDisclosure()
  return <>
  <Button 
           leftIcon={<AddIcon/>}
           colorScheme="teal"
           onClick={onOpen}
           >
            Create Customer

    </Button>
    <Drawer isOpen={isOpen} onClose={onClose} size={"md"}>
          <DrawerOverlay />
          <DrawerContent>
            <DrawerCloseButton />
            <DrawerHeader>Create New Customer</DrawerHeader>
  
            <DrawerBody>
             <CreateCustomerForm fetchCustomers={fetchCustomers}/>
            </DrawerBody>
  
            <DrawerFooter>
            <Button 
           leftIcon={<CloseIcon/>}
           colorScheme="teal"
           onClick={onClose}
           >
            Close

    </Button>
            </DrawerFooter>
          </DrawerContent>
        </Drawer>

    </>
}
export default DrawerForm;
