import { Formik, Form, useField } from 'formik';
import * as Yup from 'yup';
import { FormLabel,
         Input,
         Alert,
         AlertIcon,
         Select,
         Box,
         Button,
         Stack
         } from '@chakra-ui/react';
import {saveRecord} from '../services/client.js';
import {successNotification, errorNotification} from '../services/notification.js'

const MyTextInput = ({ label, ...props }) => {
  // useField() returns [formik.getFieldProps(), formik.getFieldMeta()]
  // which we can spread on <input>. We can use field meta to show an error
  // message if the field is invalid and it has been touched (i.e. visited)
  const [field, meta] = useField(props);
  return (
    <Box>
      <FormLabel htmlFor={props.id || props.name}>{label}</FormLabel>
      <Input className="text-input" {...field} {...props} />
      {meta.touched && meta.error ? (
        <Alert className="error" status={"error"} mt={2}>
            <AlertIcon/>
            {meta.error}
        </Alert>
      ) : null}
    </Box>
  );
};



const MySelect = ({ label, ...props }) => {
  const [field, meta] = useField(props);
  return (
    <Box>
      <FormLabel htmlFor={props.id || props.name}>{label}</FormLabel>
      <Select {...field} {...props} />
      {meta.touched && meta.error ? (
        <Alert className="error" status={"error"} mt={2}>
            <AlertIcon/>
            {meta.error}
        </Alert>
      ) : null}
    </Box>
  );
};

// And now we can use these
const CreateRecordForm = ({fetchRecords}) => {
  return (
    <>
      <Formik
        initialValues={{
          name: '',
          mail: '',
          place: '',
          number: 0, // added for our checkbox
          gender: '', // added for our select
        }}
        validationSchema={Yup.object({
          name: Yup.string()
            .max(15, 'Must be 15 characters or less')
            .required('Required'),
          mail: Yup.string()
            .email('Invalid email address')
            .required('Required'),
          place: Yup.string()
             .max(20, 'Must be 20 characters or less')
             .required('Required'),
          number: Yup.number()
            .min(16,'Must be at-least 16 or above')
            .max(100, 'Must be less than 100.')
            .required(),
          gender: Yup.string()
            .oneOf(
              ['male', 'female'],
              'Invalid Gender'
            )
            .required('Required'),
        })}
        onSubmit={(record, { setSubmitting }) => {
          setSubmitting(true);
          saveRecord(record)
            .then(res => {
//                 alert("Record has been saved");
                successNotification(
                    "Record saves",
                    `${record.name} was successfully saved`
                );
                fetchRecords();
            }).catch(err => {
                console.log(err);
                errorNotification(
                    "err.code",
                    err.response.data.message
                );
            }).finally(()=>{
                setSubmitting(false);
            })
        }}
      >
        {({ isValid, isSubmitting }) => (
            <Form>
              <Stack>
                <MyTextInput
                    label="Name"
                    name="name"
                    type="text"
                    placeholder="Jane"
                  />

                  <MyTextInput
                      label="Email Address"
                      name="mail"
                      type="email"
                      placeholder="jane@formik.com"
                    />

                  <MyTextInput
                    label="Place"
                    name="place"
                    type="text"
                    placeholder="London"
                  />

                  <MyTextInput
                    label="Age"
                    name="number"
                    type="number"
                    placeholder="23"
                  />

                  <MySelect label="Gender" name="gender">
                    <option value="">Select Gender</option>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                  </MySelect>



                  <Button disabled ={!isValid || isSubmitting} type="submit">Submit</Button>
              </Stack>
            </Form>
        )}

      </Formik>
    </>
  );
};

export default CreateRecordForm;