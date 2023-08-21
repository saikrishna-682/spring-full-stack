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
         } from '@chakra-ui/react';
import CreateRecordForm from './CreateRecordForm.jsx';

const AddIcon = () => "+";
const CloseIcon = () => "x";

const CreateRecordDrawer = ({ fetchRecords}) =>{
    const { isOpen, onOpen, onClose } = useDisclosure()
        return<>
            <Button leftIcon={<AddIcon />}
                    colorScheme={"teal"}
                    onClick={onOpen}
                    rounded='full'>
                Create Record
            </Button>
            <Drawer isOpen={isOpen} onClose={onClose} size={"xl"}>
                    <DrawerOverlay />
                    <DrawerContent>
                      <DrawerCloseButton />
                      <DrawerHeader>Create new record</DrawerHeader>

                      <DrawerBody>
                        <CreateRecordForm
                            fetchRecords={fetchRecords}
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

export default CreateRecordDrawer;
