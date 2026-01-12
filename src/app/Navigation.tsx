import React from "react";
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import HomeScreen from '../screens/HomeScreen';
import ControllerScreen from '../screens/ControllerScreen';
import LayoutsScreen from '../screens/LayoutsScreen';
import SettingsScreen from '../screens/SettingsScreen';

export type RootStackParamList = {
    Home: undefined;
    Controller: undefined;
    Layouts: undefined;
    Settings: undefined;
};

const Stack = createNativeStackNavigator<RootStackParamList>();

export default function Navigation() {
    return(
        <Stack.Navigator
            screenOptions={{
                headerShown: false
            }}
        >
            <Stack.Screen name="Home" component={HomeScreen} />
            <Stack.Screen name="Controller" component={ControllerScreen} />
            <Stack.Screen name="Layouts" component={LayoutsScreen} />
            <Stack.Screen name="Settings" component={SettingsScreen} />
        </Stack.Navigator>
    );
}