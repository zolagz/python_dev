# python多环境搭建

virtualenv  安装与使用


mac下，先安装 xcode

###安装pip：

```
pip install virtualenv

```

使用

```
mkdir  env

cd env

virtualenv env_001

或者

 virtualenv env_001 --no-site-packages

```

virtualenv1.7版本后执行virtualenv env_001命令默认是带这个参数的.

安装成功，终端输出以下信息：

```
New python executable in /Users/alfred/Virtual/env/env_001/bin/python2.7
Also creating executable in /Users/alfred/Virtual/env/env_001/bin/python
Installing setuptools, pip, wheel...done.

```

###激活env_001

cd env_001

source env_001/bin/activate

```
[#Mac: env_001]$ source bin/activate
(env_001) [#Mac:temp]$

```

###指定python版本

可以使用 -p python安装路径 在创建虚拟环境的时候指定python版本

```
<!--找到python3.6版本的文件位置-->

[#Mac:env]$ which python3
/Library/Frameworks/Python.framework/Versions/3.6/bin/python3

<!--创建python3.6虚拟环境-->
[#Mac:env]$ virtualenv -p /Library/Frameworks/Python.framework/Versions/3.6/bin/python3 py36
Running virtualenv with interpreter /Library/Frameworks/Python.framework/Versions/3.6/bin/python3
Using base prefix '/Library/Frameworks/Python.framework/Versions/3.6'
New python executable in /Users/alfred/Virtual/env/py36/bin/python3
Also creating executable in /Users/alfred/Virtual/env/py36/bin/python
Installing setuptools, pip, wheel...done.
[#Mac:env]$
```

指定python2.7

```

<!--找到python2.7版本的文件位置-->

[#Mac:env]$ which python2.7
/usr/local/bin/python2.7

<!--创建python2.7虚拟环境-->

[#Mac:env]$ virtualenv -p /usr/local/bin/python2.7 py27
Running virtualenv with interpreter /usr/local/bin/python2.7
New python executable in /Users/alfred/Virtual/env/py27/bin/python2.7
Also creating executable in /Users/alfred/Virtual/env/py27/bin/python
Installing setuptools, pip, wheel...done.
[#Mac:env]$

<!--查看python 版本 -->
[#Mac:py27]$ source bin/activate
(py27) [#Mac:py27]$ python --version
Python 2.7.14
(py27) [#Mac:py27]$

```

#### 安装scrapy
```
pip install scrapy

```

*附：遇到问题执行：*

```
xcode-select --install
```

#### 查看安装的package

```
pip freeze

```

#### 生成requirements.txt文件

根据这个文件可以在另一台机器上快速生成完全一样的开发环境。

根据环境生成requirements.txt文件的方法是:

	$ pip freeze > requirements.txt

注意: 执行前面一条命令的时候必须先进入engchen问价夹:

	$ cd env_001

否则生成的requirements.txt文件是在ProjectsEnv文件夹中的，而不是在env_001文件夹中。

将来使用生成requirements.txt文件恢复开发环境的只需要执行

	$ pip install -r requirements.txt

配置完虚拟python开发环境以后自然是要推出了，先

###退出虚拟环境 执行

	$ deactivate
	
### 生成可打包环境

某些特殊需求下，可能没有网络，我们期望打包一个env，可以解压后直接使用，这时候可以使用virtualenv -relocatable 指令将env修改为可更改位置的env

```

<!--进入到想要打包的虚拟环境中-->
[#Mac:env]$ cd py27

[#Mac:py27]$ ls
bin                include            lib                pip-selfcheck.json

<!--对当前已经创建的虚拟环境更改为可迁移-->
<!--执行  virtualenv --relocatable ./ -->
[#Mac:py27]$ virtualenv --relocatable ./

Making script /Users/alfred/Virtual/env/py27/bin/easy_install relative
Making script /Users/alfred/Virtual/env/py27/bin/easy_install-2.7 relative
Making script /Users/alfred/Virtual/env/py27/bin/pip relative
Making script /Users/alfred/Virtual/env/py27/bin/pip2 relative
Making script /Users/alfred/Virtual/env/py27/bin/pip2.7 relative
Making script /Users/alfred/Virtual/env/py27/bin/python-config relative
Making script /Users/alfred/Virtual/env/py27/bin/wheel relative
[#Mac:py27]$

```






<!--
create time: 2018-06-17 13:46:08
Author: Alfred

This file is created by Marboo<http://marboo.io> template file $MARBOO_HOME/.media/starts/default.md
本文件由 Marboo<http://marboo.io> 模板文件 $MARBOO_HOME/.media/starts/default.md 创建
-->

