import React, { useState } from "react";
import { View, Text, TouchableHighlight } from "react-native";
import styles from "./CourseMenu.style";
import AppButton from "../../../components/AppButton/AppButton";
import { deleteCourse } from "../../../api/services/courseService";
import Toast from "../../../components/Toast/Toast";
import AppModal from "../../../components/AppModal/AppModal";

const CourseMenu = ({ navigation, route }) => {
  const [course, setCourse] = useState(route.params?.course);
  const [error, setError] = useState(false);
  const [visible, setVisible] = useState(false);
  const [editMode, setEditMode] = useState(route.parms?.editMode);
  const [selectedGrade, setSelectedGrade] = useState();

  const onDeleteCourse = () => {
    deleteCourse(course.number)
      .then((res) => {
        if (res != undefined) {
          setVisible(true);
          setTimeout(() => {
            setVisible(false);
            navigation.navigate("Fächer", { deleteCourse: true });
          }, 1000);
        } else {
          throw new Error();
        }
      })
      .catch((err) => {
        setError(true);
        setTimeout(() => {
          setError(false);
        }, 3000);
      });
  };

  const onChangeGrade = () => {
    setEditMode(true);
  };

  return (
    <View style={styles.container}>
      {editMode ? (
        <View style={styles.modalView}>
          <Text
            style={{ ...styles.modalText, fontWeight: "bold", fontSize: 20 }}
          >
            Veranstaltung: {course.description}
          </Text>
          <TextInput
            style={styles.textInput}
            keyboardType="number-pad"
            onChangeText={(text) => setSelectedGrade(text)}
            value={selectedGrade}
          />
          <TouchableHighlight
            style={styles.modalButton}
            onPress={() => console.log("Ändern wurde ausgewählt")}
          >
            <Text style={styles.textStyle}>Speichern</Text>
          </TouchableHighlight>
          <TouchableHighlight
            style={{ ...styles.modalButton, marginTop: 10 }}
            onPress={() => navigation.pop()}
          >
            <Text style={styles.textStyle}>Abbrechen</Text>
          </TouchableHighlight>
        </View>
      ) : (
        <View style={styles.modalView}>
          <Text
            style={{ ...styles.modalText, fontWeight: "bold", fontSize: 20 }}
          >
            Veranstaltung: {course.description}
          </Text>
          <TouchableHighlight
            style={styles.modalButton}
            onPress={() => onChangeGrade()}
          >
            <Text style={styles.textStyle}>Note ändern</Text>
          </TouchableHighlight>
          <TouchableHighlight
            style={{ ...styles.modalButton, backgroundColor: "#f00" }}
            onPress={() => onDeleteCourse()}
          >
            <Text style={styles.textStyle}>Löschen</Text>
          </TouchableHighlight>
          <TouchableHighlight
            style={{ ...styles.modalButton, marginTop: 25 }}
            onPress={() => navigation.pop()}
          >
            <Text style={styles.textStyle}>Abbrechen</Text>
          </TouchableHighlight>
        </View>
      )}

      <AppModal header="Veranstaltung:" description={course.description}>
        <AppButton
          onPress={() => console.log("Ändern ausgewählt")}
          text="Note ändern"
        />
        <AppButton
          color="red"
          onPress={() => onDeleteCourse()}
          text="Kurs löschen"
        />
        <AppButton onPress={() => navigation.pop()} text="Abbrechen" />
      </AppModal>
      <Toast color="red" showModal={error} text="Keine Verbindung zum Server" />
      <Toast
        color="green"
        showModal={visible}
        text="Kurs erfolgreich gelöscht"
      />
    </View>
  );
};

export default CourseMenu;
