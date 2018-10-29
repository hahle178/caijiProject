var path = require("path");
var webpack = require('webpack');

module.exports = {
    entry: './src/index.js',//如在非webkit内核下运行请设置此项值为["babel-polyfill", './src/index.js'],
    devServer: {
        contentBase: './dist/static',
        proxy: {
            '/system': {
                target: 'http://localhost:8081',
                pathRewrite: {'^/system': ''},
            }
        }
    },
    output: {
        filename: 'bundle.js',
        path: path.resolve(__dirname, './dist/static/bundles'),
        publicPath: '/bundles/'
    },
    module: {

        rules: [{
            test: /\.jpg$/,
            loader: "file-loader"
        }, {
            test: /\.html$/,
            loader: "art-template-loader",
            options: {
                // art-template options (if necessary)
                // imports: require.resolve("./template-runtime"),
                htmlResourceRules: false
                //htmlResourceRoot: __dirname,
                //root: path.resolve(__dirname)
            }
        }, {
            test: /\.js$/,
            use: [{
                loader: 'babel-loader?cacheDirectory=true',
                options: {
                    presets: [['es2015', {modules: false}]],
                    plugins: [
                        'syntax-dynamic-import'
                    ]
                }

            }],
        }]
    },
    plugins: [
        new webpack.DllReferencePlugin({
            manifest: require('./dist/static/bundles/manifest.json'),
            context: __dirname
        })
    ],
    externals: {
        jquery: "jQuery" // 打包bundle.js文件时忽略jquery
    }
};