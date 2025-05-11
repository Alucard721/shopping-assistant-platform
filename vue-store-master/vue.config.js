/*
 * @Description: 配置文件
 * @Author: hai-27
 * @Date: 2020-02-07 16:23:00
 * @LastEditors: hai-27
 * @LastEditTime: 2021-03-03 22:32:57
 */
module.exports = {
  publicPath: './',
  lintOnSave: false,

  pluginOptions: {
    electronBuilder: {
      // electron 构建配置
      builderOptions: {
        productName: '优易购', // 项目名，也是生成的安装文件名，即electron-admin-element-vue.exe
        nsis: {
          oneClick: false, // 是否一键安装
          allowElevation: true, // 允许请求提升。 如果为false，则用户必须使用提升的权限重新启动安装程序。
          allowToChangeInstallationDirectory: true, // 允许修改安装目录
          installerIcon: './build/icons/icon.ico', // 安装图标
          uninstallerIcon: './build/icons/icon.ico', // 卸载图标
          installerHeaderIcon: './build/icons/icon.ico', // 安装时头部图标
          createDesktopShortcut: true, // 创建桌面图标
          shortcutName: '优易购' // 图标名称
        },
        dmg: { // macOSdmg
          contents: [
            {
              "x": 410,
              "y": 150,
              "type": "link",
              "path": "/Applications"
            },
            {
              "x": 130,
              "y": 150,
              "type": "file"
            }
          ]
        },
        win: { // win 相关配置
          icon: './build/icons/icon.ico',
          artifactName: '${productName}-v${version}-win32-setup.${ext}',
          target: [
            {
              target: "nsis", // 利用nsis制作安装程序
              arch: [ // 这个意思是打出来32 bit + 64 bit的包，但是要注意：这样打包出来的安装包体积比较大，所以建议直接打32的安装包。
                // "x64",
                "ia32"
              ]
            }
          ]
        },
        linux: {
          icon: "./build/icons",
          artifactName: '${productName}-v${version}-linux.${ext}'
        }
      }
    }
  }
}
