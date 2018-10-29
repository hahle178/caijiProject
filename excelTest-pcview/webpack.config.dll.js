const path = require('path');
const webpack = require('webpack');

module.exports = {
    entry: {
        vendors: ['ezdev-pcview', 'ezdev-system']
    },
    output: {
        filename: 'vendor.js',
        path: path.resolve(__dirname, './dist/static/bundles/'),
        library: 'vendor', // 当前的所有内容都会存放在这个参数指定变量名的一个全局变量下，注意与DllPlugin的name参数保持一致
        publicPath: '/bundles/',
    },
    module: {

        rules: [{
            test: /\.jpg$/,
            loader: "file-loader"
        }, {
            test: /\.html$/,
            loader: "art-template-loader",
            options: {
                htmlResourceRules: false
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
            }]
        }, {
            test: /\.(png|jpg|gif|jpeg|woff|woff2|svg|eot|ttf)$/,
            use: [{
                loader: 'url-loader',
                options: {
                    limit: 8192
                }
            }]
        },
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            },
            {
                test: /\.less$/,
                use: ['style-loader', 'css-loader', 'less-loader']
            }
        ]
    },
    plugins: [
        new webpack.DllPlugin({
            path: path.resolve(__dirname, './dist/static/bundles/manifest.json'),// 本Dll文件中各模块的索引，供DllReferencePlugin读取使用
            name: 'vendor'
        })
    ]
};