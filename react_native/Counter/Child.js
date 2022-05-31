import React from 'react';
import type {Node} from 'react';

import {
  View,
  Text,
  Button,
} from 'react-native';

export const Child = ({title, feedback}): Node => {
  return (
    <View>
      <Text>
          {title}
      </Text>
      <Button
        title="feedback"
        onPress={() => { feedback("toParent") }}
      />
    </View>
  );
};
