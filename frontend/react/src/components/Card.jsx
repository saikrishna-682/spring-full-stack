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
  Tag
} from '@chakra-ui/react'

export default function CardWithImage({id,name,mail,place,number,gender}) {
    gender = gender === "male"? "men" : "women";
  return (
    <Center py={6}>
      <Box
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

{/*           <Button */}
{/*             w={'full'} */}
{/*             mt={8} */}
{/*             bg={useColorModeValue('#151f21', 'gray.900')} */}
{/*             color={'white'} */}
{/*             rounded={'md'} */}
{/*             _hover={{ */}
{/*               transform: 'translateY(-2px)', */}
{/*               boxShadow: 'lg', */}
{/*             }}> */}
{/*             Follow */}
{/*           </Button> */}
        </Box>
      </Box>
    </Center>
  )
}