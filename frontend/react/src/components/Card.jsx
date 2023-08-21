'use client'

import {
  Heading,
  Avatar,
  Box,
  Center,
  Image,
  Flex,
  Text,
  Stack,
  Button,
  useColorModeValue,
  Tag,
  useDisclosure,
  AlertDialog,
  AlertDialogBody,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogContent,
  AlertDialogOverlay,
  AlertDialogCloseButton,
  FormLabel,
  Input,
  Alert,
  AlertIcon,
  Select,
} from '@chakra-ui/react'
import { Formik, Form, useField } from 'formik';
import * as Yup from 'yup';
import {successNotification, errorNotification} from '../services/notification.js'
import {deleteRecord, updateRecord} from '../services/client.js';
import {useRef} from 'react';
import {FetchRecords} from '../App.jsx';
import CreateRecordForm from  './CreateRecordForm.jsx'
import UpdateRecordDrawer from './UpdateRecordDrawer.jsx'




export default function CardWithImage({id,name,mail,place,number,gender}) {
    gender = gender === "male"? "men" : "women";
    const { isOpen, onOpen, onClose } = useDisclosure()
    const cancelRef = useRef()
  return (
    <Center py={6}>
      <Box
        minW={'300px'}
        maxW={'300px'}
        w={'full'}
        bg={useColorModeValue('white', 'gray.800')}
        boxShadow={'2xl'}
        rounded={'md'}
        overflow={'hidden'}>
        <Image
          h={'120px'}
          w={'full'}
          src={
            'https://images.unsplash.com/photo-1612865547334-09cb8cb455da?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80'
          }
          objectFit="cover"
          alt="#"
        />
        <Flex justify={'center'} mt={-12}>
          <Avatar
            size={'xl'}
            src={`https://randomuser.me/api/portraits/${gender}/${number}.jpg`}
            css={{
              border: '2px solid white',
            }}
          />
        </Flex>

        <Box p={6}>
          <Stack spacing={1} align={'center'} mb={5}>
            <Tag borderRadius='full'>{id}</Tag>
            <Heading fontSize={'2xl'} fontWeight={500} fontFamily={'body'}>
              {name}
            </Heading>
            <Text color={'gray.500'}>{mail}</Text>
          </Stack>

          <Stack direction={'row'} justify={'center'} spacing={6}>
            <Stack spacing={0} align={'center'}>
              <Text fontWeight={600}>{place}</Text>
              <Text fontSize={'sm'} color={'gray.500'}>
                Location
              </Text>
            </Stack>


            <Stack spacing={0} align={'center'}>
              <Text fontWeight={600}>{number}</Text>
              <Text fontSize={'sm'} color={'gray.500'}>
                Age
              </Text>
            </Stack>

          </Stack>
{/* // DELETE BUTTON */}
          <Stack direction='row' justify='center'
           spacing='2'>
              <Button mt='2'
                  bg={'red.400'}
                  color={'white'}
                  rounded={'full'}
                  _hover={{
                      transform: 'translateY(-2px)',
                      boxShadow: 'lg'
                  }}
                  _focus={{
                      bg: 'green.500'
                  }}
                  onClick={onOpen}

              >
                  Delete
              </Button>
              <AlertDialog
              isOpen={isOpen}
              leastDestructiveRef={cancelRef}
              onClose={onClose}
              >
              <AlertDialogOverlay>
                <AlertDialogContent>
                  <AlertDialogHeader fontSize='lg' fontWeight='bold'>
                    Delete Record
                  </AlertDialogHeader>

                  <AlertDialogBody>
                    Do you want to delete {name}? You can't undo this action afterwards.
                  </AlertDialogBody>

                  <AlertDialogFooter>
                    <Button ref={cancelRef} onClick={onClose}>
                      Cancel
                    </Button>
                    <Button colorScheme='red' onClick={()=>{
                      deleteRecord(id).then(res=>{
                          successNotification(
                              'Record Deletion',
                              `${name} has been successfully deleted..`
                          )
                          FetchRecords();
                          }).catch(err=>{
                              console.log(err);
                              errorNotification(
                                  "err.code",
                                  err.response.data.message
                              );
                          }).finally(()=>{
                              onClose()
                          })
                    }} ml={3}>
                      Delete
                    </Button>
                  </AlertDialogFooter>
                </AlertDialogContent>
              </AlertDialogOverlay>
            </AlertDialog>
          </Stack>
        </Box>
        <Stack justify='center' direction='row' >
            <Stack>
                <UpdateRecordDrawer
                    initialValues={{name,mail,place,number}}
                    recordId={id}
                />
            </Stack>
          <Stack spacing={0} align={'center'}>
{/*               <Button */}
{/*                   mt={2} */}
{/*                   bg={'cyan.400'} */}
{/*                   color={'white'} */}
{/*                   rounded={'full'} */}
{/*                   _hover={{ */}
{/*                       transform: 'translateY(-2px)', */}
{/*                       boxShadow: 'lg' */}
{/*                   }} */}
{/*                   _focus={{ */}
{/*                       bg: 'green.500' */}
{/*                   }} */}
{/*                   onClick={onOpen} */}

{/*               > */}
{/*                   Update */}
{/*               </Button> */}
              <AlertDialog
              isOpen={isOpen}
              leastDestructiveRef={cancelRef}
              onClose={onClose}
              >
              <AlertDialogOverlay>
                <AlertDialogContent>
                  <AlertDialogHeader fontSize='lg' fontWeight='bold'>
                    Update Record
                  </AlertDialogHeader>

                  <AlertDialogBody>
                    Do you want to update {name}?
                  </AlertDialogBody>

                  <AlertDialogFooter>
                    <Button ref={cancelRef} onClick={onClose}>
                      Cancel
                    </Button>
                    <Button colorScheme='red' onClick={()=>{
                      deleteRecord(id).then(res=>{
                          successNotification(
                              'Record Deletion',
                              `${name} has been successfully deleted..`
                          )
                          FetchRecords();
                          }).catch(err=>{
                              console.log(err);
                              errorNotification(
                                  "err.code",
                                  err.response.data.message
                              );
                          }).finally(()=>{
                              onClose()
                          })
                    }} ml={3}>
                      Update
                    </Button>
                  </AlertDialogFooter>
                </AlertDialogContent>
              </AlertDialogOverlay>
            </AlertDialog>
          </Stack>
        </Stack>
      </Box>
    </Center>
  )
}