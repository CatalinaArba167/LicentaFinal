const path = require('path');

module.exports = {
  resolve: {
    fallback: {
      'https': require.resolve('https-browserify'),
      'crypto': require.resolve('crypto-browserify'),
      'stream': require.resolve('stream-browserify'),
      'assert': require.resolve('assert/')
    }
  },
  module: {
    rules: [
      {
        test: /\.scss$/,
        use: [
          'to-string-loader',
          'css-loader',
          'sass-loader'
        ],
        include: path.resolve(__dirname, '../src/app')
      }
    ]
  }
};
