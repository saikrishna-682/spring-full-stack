import { Button,
         ButtonGroup,
         Drawer,
         DrawerBody,
         DrawerFooter,
         DrawerHeader,
         DrawerOverlay,
         DrawerContent,
         DrawerCloseButton,
         useDisclosure,
         Input,
         Stack
         } from '@chakra-ui/react';
import UpdateRecordForm from './UpdateRecordForm.jsx';

const AddIcon = () => "+";
const CloseIcon = () => "x";

const UpdateRecordDrawer = ({ fetchRecords, initialValues, recordId}) =>{
    const { isOpen, onOpen, onClose } = useDisclosure()
        return<>
        <Stack >
            <Button  ml='4' mb='2' mt='-5'
                     spacing='0'
                     direction='column'
                     bg={'cyan.400'}
                     color={'white'}
                     rounded={'full'}
                     _hover={{
                         transform: 'translateY(-2px)',
                          boxShadow: 'lg'
                     }}
                     _focus={{
                       bg: 'cyan.500'
                     }}
                      onClick={onOpen}  >
                Update
            </Button>
        </Stack>
            <Drawer isOpen={isOpen} onClose={onClose} size={"xl"}>
                    <DrawerOverlay />
                    <DrawerContent>
                      <DrawerCloseButton />
                      <DrawerHeader>Update Record</DrawerHeader>

                      <DrawerBody>
                        <UpdateRecordForm
                            fetchRecords={fetchRecords}
                            initialValues = {initialValues}
                            recordId={recordId}
                        />
                      </DrawerBody>

                      <DrawerFooter>
                        <Button leftIcon={<CloseIcon />}
                            colorScheme={"teal"}
                            onClick={onClose} >
                            Close
                        </Button>
                      </DrawerFooter>
                    </DrawerContent>
                  </Drawer>
        </>
}

export default UpdateRecordDrawer;
