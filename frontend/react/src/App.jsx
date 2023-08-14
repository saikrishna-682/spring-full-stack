import { Button,
         ButtonGroup,
         Spinner,
         Text,
         Wrap,
         WrapItem } from '@chakra-ui/react'
import SidebarWithHeader from './components/shared/SideBar.jsx';
import { useEffect, useState } from 'react';
import { getRecords } from './services/client.js';
import  CardWithImage  from './components/Card.jsx'


const FetchRecords = () =>{
    const[records, setRecords] = useState([]);
    const[loading, setLoading] = useState(false);

        useEffect(()=>{
            setLoading(true);
            getRecords().then(res => {
                setRecords(res.data);
            }).catch(err => {
                console.log(err);
            }).finally(()=>{
                setLoading(false);
            })
        },[])

        if(loading){
            return(
                <SidebarWithHeader>
                    <Spinner
                      thickness='4px'
                      speed='0.65s'
                      emptyColor='gray.200'
                      color='blue.500'
                      size='xl'
                    />
                </SidebarWithHeader>
            )
        }

        if(records.length <= 0){
            return (
                <SidebarWithHeader>
                    <Text>No Record's Available...</Text>
                </SidebarWithHeader>
            )
        }
    return(
        <SidebarWithHeader>
            <Wrap justify={"center"} spacing={"30px"}>
               {records.map((record, index) => (
                    <WrapItem key={index}>
                        <CardWithImage
                            {...record}
                        />
                    </WrapItem>
               ))}
            </Wrap>
        </SidebarWithHeader>
    )
}





const App = () => {


    return (
        <FetchRecords />

        )
}


export default App






















//===================================================================
// import UserProfile from './UserProfile.jsx';
// import { useState, useEffect } from 'react';
//
// const users = [
//     {
//     name: "Jamila",
//     age: 23,
//     gender: "FEMALE"
//     },
//     {
//     name: "Jaden",
//     age: 33,
//     gender: "MALE"
//     },
//     {
//     name: "Kilbil",
//     age: 11,
//     gender: "MALE"
//     },
//     {
//     name: "Maggie",
//     age: 45,
//     gender: "FEMALE"
//     }
//
// ]
//
// const UserProfiles = () => {
//     return (
//         <div>
//                     {users.map((user,index)=>(
//                      <UserProfile
//                         key={index}
//                         name = {user.name}
//                         age={user.age}
//                         gender = {user.gender}
//                         autoNumber={index}
//                     />
//                     ))}
//         </div>
//     )
//
// }
//
//
// function App(){
//     const [counter, setCounter] = useState(0);
//     const[isLoading, setIsLoading] = useState(false);
//
//     useEffect(()=>{
//         setIsLoading(true)
//         setTimeout(() => {
//             setIsLoading(false)},4000)
//
//     },[])
//
//     if(isLoading){
//     return "loading...";
//     }
//     return(
//         <div>
//             <button onClick={() => setCounter(counter +1)}>Increment Counter</button>
//             <p>Counter : {counter}</p>
//             <UserProfiles />
//         </div>
//
//     )
// }
//
//
//
//
// // function App() {
// //     return(
// //
// //     <div>
// //         <UserProfiles />
// //
// //
// //
// //
// //         < UserProfile
// //             name={"Alex"}
// //             age={23}
// //             gender={"men"}>
// //             </UserProfile>
// //         < UserProfile
// //             name={"Jamila"}
// //             age={32}
// //             gender={"women"}>
// //
// //                 <h1>THis is how we use ...props for children</h1>
// //                                 <p>this is how to access children properties using
// //                                 ...props and as well as a notation saying
// //                                 props.children property
// //                                 <b>-- to access the rest of the properties we use ...props annotation within the component</b>
// //                                 <b>-- and use the flower brackets to invoke that property by saying props.children using
// //                                     flower brackets</b></p>
// //
// //             </UserProfile>
// //
// //      </div>
// //     )
// // }
//
// export default App;
