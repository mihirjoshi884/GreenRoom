const path = require('path');

module.exports = {
  entry: './src/index.js', // Adjust the entry point based on your project structure
  output: {
    filename: 'bundle.js', // Adjust the output filename based on your preferences
    path: path.resolve(__dirname, 'dist'), // Adjust the output path based on your project structure
  },
  resolve: {
    fallback: {
      crypto: require.resolve('crypto-browserify'),
      stream: require.resolve('stream-browserify'),
    },
  },
  // ... other webpack configuration options
};
