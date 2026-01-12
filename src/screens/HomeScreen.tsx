import React from 'react';
import { View, Text, StyleSheet } from 'react-native';
import PrimaryButton from '../components/PrimaryButton';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { RootStackParamList } from '../app/Navigation';

type Props = NativeStackScreenProps<RootStackParamList, 'Home'>;

export default function HomeScreen({ navigation }: Props) {
    return(
        <View style={styles.container}>
            <Text style={styles.title}>ControllerApp</Text>
            
            <PrimaryButton 
                title="Start Controller"
                onPress={() => navigation.navigate('Controller')}
            />

            <PrimaryButton 
                title="Layouts"
                onPress={() => navigation.navigate('Layouts')}
            />

            <PrimaryButton 
                title="Settings"
                onPress={() => navigation.navigate('Settings')}
            />
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#000',
        justifyContent: 'center',
        alignItems: 'center',
        padding: 24
    },
    title: {
        color: '#fff',
        fontSize: 32,
        fontWeight: 'bold',
        marginBottom: 40
    }
});