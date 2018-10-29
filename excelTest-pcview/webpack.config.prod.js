const path = require('path');
const CopyWebpackPlugin = require('copy-webpack-plugin');

module.exports = {
    entry: './index.js',
    output: {
        filename: 'index.js',
        path: path.resolve(__dirname, './build'),
    },
    plugins: [
        new CopyWebpackPlugin([
            { from: 'src' },
            { from: './package.json'},
            { from: './README.md'}

        ], {
            ignore: [
                // Doesn't copy any files with a txt extension
                '*.txt',
            ],

            // By default, we only copy modified files during
            // a watch or webpack-dev-server build. Setting this
            // to `true` copies all files.
            copyUnmodified: true
        })
    ]
};