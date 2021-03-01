const CopyPlugin = require('copy-webpack-plugin')
const path = require("path")
module.exports = {
  outputDir: path.resolve(__dirname,"../../src/main/resources/static/"),
  indexPath: "../templates/index.html",

  pages: {
    index: {
      entry: 'src/index.js',
      template: 'public/index.html',
      filename: 'index.html'
    }
  },
  devServer: {
    clientLogLevel: 'warning',
    hot: true,
    contentBase: 'dist',
    compress: true,
    open: true,
    overlay: { warnings: false, errors: true },
    publicPath: '/',
    quiet: true,
    watchOptions: {
      poll: false,
      ignored: /node_modules/
    },
    port: 3000,
    proxy: {
      '/api': {
        target: 'http://localhost:8080'
        // target: 'http://ec2-3-35-148-240.ap-northeast-2.compute.amazonaws.com:8080'
      }
    }
  },

  chainWebpack: config => {
    config.plugins.delete('prefetch-index'),
    config.module
      .rule('vue')
        .use('vue-loader')
          .tap(args => {
            args.compilerOptions.whitespace = 'preserve'
          })
  },
  productionSourceMap: false,
  assetsDir: './assets/',
  configureWebpack: {
    plugins: [
      new CopyPlugin({
        patterns: [
          { from: 'src/assets/img', to: 'assets/img' },
          { from: 'src/assets/logos', to: 'assets/logos' },
          { from: 'src/assets/fonts', to: 'assets/fonts' }
        ],
      }),
    ]
  }
}
