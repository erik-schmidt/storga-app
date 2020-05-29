import React, { useState } from "react";
import styles from "./CalendarInformationModal.style";
import { View, Text } from "react-native";
import { FontAwesome5 } from "@expo/vector-icons";
import moment from "moment";

const CalendarInformationModal = ({ navigation, route }) => {
  navigation.setOptions({
    headerRight: () => (
      <FontAwesome5.Button
        name="edit"
        color="black"
        backgroundColor="#ffff"
        onPress={() =>
          navigation.navigate("CalendarMenu", {
            editMode: true,
            appointment: appointment,
          })
        }
      />
    ),
  });
  const [appointment, setAppointments] = useState(route.params?.appointment);

  return (
    <View style={styles.container}>
      <Text style={styles.header}>Termin: {appointment.name}</Text>
      <Text style={styles.text}>
        Startzeit : {moment(appointment.entryStartTime).format("LT")} Uhr
      </Text>
      <Text style={styles.text}>
        Endzeit : {moment(appointment.entryFinishTime).format("LT")} Uhr
      </Text>
      <Text style={styles.text}>Infos: {appointment.description}</Text>
    </View>
  );
};

export default CalendarInformationModal;
