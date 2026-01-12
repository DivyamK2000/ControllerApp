import React from 'react';
import { Pressable, Text, StyleSheet } from 'react-native';

type Props = {
    title: string;
    onPress: () => void;
}

export default function PrimaryButton({ title, onPress } : Props) {
    return(
        <Pressable style={styles.button} onPress={onPress}>
            <Text style={styles.text}>{title}</Text>
        </Pressable>
    );
}

const styles = StyleSheet.create({
    button: {
        width: '80%',
        paddingVertical: 16,
        backgroundColor: '#1e90ff',
        borderRadius: 10,
        marginVertical: 10
    },
    text: {
        color: '#fff',
        fontSize: 18,
        fontWeight: '600',
        textAlign: 'center'
    }
});