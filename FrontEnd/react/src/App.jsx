import { Wrap,
WrapItem,
Spinner,
Text} from '@chakra-ui/react'
import SidebarWithHeader from './components/shared/sidebar';
import {useEffect,useState } from 'react';
import {getCustomers} from './services/client.js';
import CardWithImage from './components/Card';
import React from 'react';
import { errorNotification } from './services/container.js';
import DrawerForm from './components/CreateCustomerDrawer.jsx';
const App = ()=>{

  const [customers,setCustomers] = useState([]);
  const [Loading,setLoading] = useState(false);
  const [err,setError]= useState("");
  const fetchCustomers = ()=>{
     setLoading(true);
     getCustomers().then(res=>{
        setCustomers(res.data);
     }).catch(err=>{
          setError( err.response.data.message);
          errorNotification(
               err.code,
               err.response.data.message
           )
     }).finally(()=>{
     setLoading(false);
     })
  }
     useEffect(()=>{
     
     fetchCustomers();

     },[])
      if(Loading){
       return( <SidebarWithHeader>
         <Spinner
           thickness='4px'
           speed='0.65s'
           emptyColor='gray.200'
           color='blue.500'
           size='xl'
         />
         </SidebarWithHeader>)
      }

     if(err){
          return(
               <SidebarWithHeader>
                      <DrawerForm fetchCustomers={fetchCustomers}/>
                        <Text mt={"25px"}>Oops There was an error.</Text>
                        </SidebarWithHeader>
                        )
     }

      if(customers.length<=0){
      return(
       <SidebarWithHeader>
              <DrawerForm fetchCustomers={fetchCustomers}/>
                <Text mt={"25px"}>No one is here.</Text>
                </SidebarWithHeader>
                )
      }

     return (
          <SidebarWithHeader>
           <DrawerForm fetchCustomers={fetchCustomers}/>
          <Wrap justify = {"center"} spacing = {"30px"}>
         {customers.map((customer,index)=>(
         <WrapItem key = {index}>
         <CardWithImage 
         {...customer}
         imageNumber = {index}
         fetchCustomers = {fetchCustomers}
         /></WrapItem>
         ))}</Wrap>
          </SidebarWithHeader>
     )
}

export default App;