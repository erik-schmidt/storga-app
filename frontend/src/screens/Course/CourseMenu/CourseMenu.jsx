import React, { useState } from "react";
import { View, TextInput } from "react-native";
import styles from "./CourseMenu.style";
import AppButton from "../../../components/AppButton/AppButton";
import { deleteCourse } from "../../../api/services/CourseService";
import AppModal from "../../../components/AppModal/AppModal";
import * as HttpStatus from "http-status-codes";
import AuthContext from "../../../constants/AuthContext";

const CourseMenu = ({ navigation, route }) => {
  const [course, setCourse] = useState(route.params?.course);
  const [editMode, setEditMode] = useState(route.parms?.editMode);
  const [selectedGrade, setSelectedGrade] = useState();
  const { signOut } = React.useContext(AuthContext);

  const onDeleteCourse = () => {
    deleteCourse(course.number)
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          navigation.navigate("Fächer", { courseDeleted: true });
        } else if (res.status === HttpStatus.UNAUTHORIZED) {
          signOut();
        } else {
          throw new Error(res.data);
        }
      })
      .catch((err) => {
        alert(err);
      });
  };

  const onChangeGrade = () => {
    setEditMode(true);
  };

  return (
    <View style={styles.container}>
      {editMode ? (
        <View>
          <AppModal header="Note eintragen" description={course.description}>
            <TextInput
              style={styles.textInput}
              keyboardType="number-pad"
              placeholder="Note"
              onChangeText={(text) => setSelectedGrade(text)}
              value={selectedGrade}
            />
            <AppButton
              onPress={() => console.log("Ändern ausgewählt")}
              text="Speichern"
            />
            <AppButton onPress={() => navigation.pop()} text="Abbrechen" />
          </AppModal>
        </View>
      ) : (
        <View>
          <AppModal header="Veranstaltung:" description={course.description}>
            <AppButton onPress={() => onChangeGrade()} text="Note ändern" />
            <AppButton
              color="red"
              onPress={() => onDeleteCourse()}
              text="Kurs löschen"
            />
            <AppButton onPress={() => navigation.pop()} text="Abbrechen" />
          </AppModal>
          <Toast
            color="red"
            showModal={error}
            text="Keine Verbindung zum Server"
          />
          <Toast
            color="green"
            showModal={visible}
            text="Kurs erfolgreich gelöscht"
          />
        </View>
      )}
    </View>
  );
};

export default CourseMenu;
