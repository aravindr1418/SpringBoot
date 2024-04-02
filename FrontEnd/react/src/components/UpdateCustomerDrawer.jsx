import {  Button,
    Drawer,
    DrawerBody,
    DrawerCloseButton,
    DrawerFooter,
    DrawerHeader,
    DrawerOverlay,useDisclosure,DrawerContent} from "@chakra-ui/react";
import UpdateCustomerForm from "./UpdateCustomerForm";
    const AddIcon =()=>"+";
    const CloseIcon =()=>"x";
    
    const UpdateCustomerDrawer = ({fetchCustomers,initialValues,customerId})=>{
        const { isOpen, onOpen, onClose } = useDisclosure()
      return <>
        <Button 
          bg={'gray.200'}
          color={'black'}
          rounded={'full'}
          _hover={{
            transform :'translateY(-2px)',
            boxShadow:'lg'
          }}
          onClick={onOpen}
          >
         Update Customer
          </Button>
        <Drawer isOpen={isOpen} onClose={onClose} size={"md"}>
              <DrawerOverlay />
              <DrawerContent>
                <DrawerCloseButton />
                <DrawerHeader>Update Customer</DrawerHeader>
      
                <DrawerBody>
                 <UpdateCustomerForm fetchCustomers={fetchCustomers}
                 initialValues = {initialValues}
                 customerId={customerId}/>
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
    export default UpdateCustomerDrawer;
    