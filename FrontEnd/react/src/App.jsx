import { Wrap,
WrapItem,
Spinner,
Text} from '@chakra-ui/react'
import SidebarWithHeader from './components/shared/sidebar';
import {useEffect,useState } from 'react';
import {getCustomers} from './services/client.js';
import CardWithImage from './components/Card';
import React from 'react';
const App = ()=>{

  const [customers,setCustomers] = useState([]);
  const [Loading,setLoading] = useState(false);
     useEffect(()=>{
      setLoading(true);
          getCustomers().then(res=>{
             setCustomers(res.data);
          }).catch(err=>{
               console.log(err);
          }).finally(()=>{
          setLoading(false);
          })


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
      if(customers.length<=0){
      return(
       <SidebarWithHeader>
                <Text>No one is here.</Text>
                </SidebarWithHeader>
                )
      }

     return (
          <SidebarWithHeader>
          <Wrap justify = {"center"} spacing = {"30px"}>
         {customers.map((customer,index)=>(
         <WrapItem key = {index}>
         <CardWithImage {...customer}/></WrapItem>
         ))}</Wrap>
          </SidebarWithHeader>
     )
}

export default App;