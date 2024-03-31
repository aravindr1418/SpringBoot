import UserProfile from "./UserProfile";
import { useState , useEffect} from "react";
    const users = [
     {
          name:"Aravind",
          age:23,
          gender:"MALE"
     },
     {
          name:"Aswin",
          age:43,
          gender:"MALE"
     },
     {
          name:"Meena",
          age:45,
          gender:"FEMALE"
     },
     {
          name:"Savitha",
          age:25,
          gender:"FEMALE"
     },
     {
          name:"Sabarishan",
          age:49,
          gender:"MALE"
     },
     {
          name:"Palani",
          age:76,
          gender:"MALE"
     },
     {
          name:"Rajendiran",
          age:53,
          gender:"MALE"
     },
    
    ]
    const UserProfiles = ({users})=>(
     <div>
   {   users.map((user,index)=>(

     <UserProfile  
         key={index}
         name = {user.name}
         age = {user.age}
         gender = {user.gender}
         imageNumber = {index}
         />
   ))}
           
     </div>
    )


    function App(){
     const [count,setCounter]=useState(0);
     const [isLoading,setIsLoading]= useState(false);

     useEffect(()=>{
         setIsLoading(true);
         setTimeout(()=>{
          setIsLoading(false);
         },4000)
         return ()=>{
          console.log("cleanup functions")
         }
     },[])
      if(isLoading){
          return <h1>Loading wait for 4 seconds...</h1>
      }

    return ( 
     <div>  
          <button 
          onClick={()=>setCounter(prevCounter=>prevCounter+1)}>
               increment count</button>
          <h1>{count}</h1>
          <UserProfiles
     users = {users}/>
     </div>
    )
}
export default App
