# 新中式极简水墨风界面改造文档

## 需求场景与处理逻辑

### 场景描述
将现有的index.html前端页面改造成新中式极简水墨风风格，营造禅意清雅的氛围感，同时保持所有原有功能不变。

### 设计风格要求
1. **背景风格**：米白色宣纸质感底色
2. **装饰元素**：
   - 左右两侧淡墨晕染的竹子剪影（竹枝纤细、竹叶稀疏，只露出边缘部分，不遮挡中心区域）
   - 背景有几座朦胧的淡墨远山
   - 云雾缭绕，几乎与底色融为一体
3. **整体风格**：极简留白，干净通透
4. **色彩限制**：
   - 墨色只有浅灰、淡墨绿
   - 对比度极低
   - 无任何鲜艳色彩
5. **功能约束**：不影响文字和按钮显示，禅意清雅，氛围感柔和

## 架构与技术方案

### 改造范围
- **主文件**：`src/main/resources/templates/index.html`
- **改造内容**：CSS样式部分（<style>标签内）
- **保留内容**：所有JavaScript逻辑、HTML结构、API调用、交互功能

### 技术实现方案
1. **CSS变量重新定义**
   - 将主色调从当前的草木绿改为淡墨绿/浅灰系列
   - 调整背景色为米白色宣纸质感
   - 降低所有色彩饱和度，保持低对比度

2. **背景层设计**
   - 使用CSS伪元素（::before, ::after）创建多层背景
   - 利用radial-gradient实现淡墨晕染效果
   - 添加宣纸纹理质感

3. **竹子与远山装饰**
   - 使用CSS形状和渐变模拟竹子剪影
   - 使用淡墨渐变创建朦胧远山
   - 确保装饰元素不影响内容可读性

4. **卡片与组件调整**
   - 保持圆角、阴影等现代UI元素
   - 将边框和背景调整为淡雅色调
   - 确保按钮和文字在淡雅背景下清晰可见

## 受影响文件

### 修改文件
- **文件路径**：`c:\Users\57602\Desktop\Demo_computer_DC\src\main\resources\templates\index.html`
- **修改类型**：CSS样式改造
- **受影响区域**：
  - `:root` CSS变量定义（第15-61行）
  - `body` 及其伪元素样式（第69-116行）
  - `.nav-wrapper` 导航栏样式（第129-214行）
  - `.section` 卡片样式（第226-286行）
  - `.push-card` 推送卡片样式（第317-388行）
  - `.tool-button` 按钮样式（第415-449行）
  - `.knowledge-card` 知识卡片样式（第454-497行）

## 实现细节

### 1. 色彩体系重新设计

```css
:root {
    /* 新中式极简水墨配色 */
    --primary: #3D5A54;          /* 淡墨绿 - 主色 */
    --primary-light: #5A7570;    /* 浅淡墨绿 */
    --primary-bg: rgba(61, 90, 84, 0.05);
    --primary-border: rgba(61, 90, 84, 0.10);

    /* 宣纸米白系列 */
    --bg-start: #FDFCF8;         /* 宣纸白 */
    --bg-end: #F7F5F0;           /* 米白底色 */

    /* 远山淡墨 */
    --mountain-light: rgba(140, 145, 142, 0.04);
    --mountain-medium: rgba(130, 135, 132, 0.06);

    /* 竹叶淡绿 */
    --bamboo-light: rgba(100, 130, 115, 0.06);
    --bamboo-medium: rgba(90, 120, 105, 0.08);

    /* 卡片背景 */
    --card-bg: rgba(255, 253, 250, 0.92);
    --card-border: rgba(180, 175, 170, 0.25);

    /* 文字层级 */
    --text-title: #3A403C;       /* 深灰，非纯黑 */
    --text-body: #565C58;        /* 中灰 */
    --text-sub: #8A8F8C;         /* 浅灰 */
    --text-tip: #B0B5B2;         /* 极浅灰 */

    /* AI蓝改为淡墨蓝 */
    --ai-blue: #6B858F;
    --ai-blue-bg: rgba(107, 133, 143, 0.06);

    /* 防骗暖棕改为淡墨棕 */
    --anti-warm: #8A7A70;
    --anti-warm-bg: rgba(138, 122, 112, 0.06);
}
```

### 2. 背景层改造

```css
body {
    background: linear-gradient(170deg, var(--bg-start), var(--bg-end));
    background-attachment: fixed;
}

/* 宣纸肌理 + 淡墨竹子 + 朦胧远山 */
body::before {
    content: '';
    position: fixed;
    inset: 0;
    background:
        /* 左侧竹子剪影（边缘，稀疏纤细） */
        radial-gradient(ellipse 90px 220px at 3% 25%, var(--bamboo-light) 0%, transparent 70%),
        radial-gradient(ellipse 70px 200px at 5% 65%, var(--bamboo-medium) 0%, transparent 75%),
        /* 右侧竹子剪影 */
        radial-gradient(ellipse 80px 210px at 97% 30%, var(--bamboo-light) 0%, transparent 70%),
        radial-gradient(ellipse 65px 190px at 95% 60%, var(--bamboo-medium) 0%, transparent 75%),
        /* 远山轮廓（多层朦胧） */
        radial-gradient(ellipse 400px 140px at 15% 88%, var(--mountain-light) 0%, transparent 55%),
        radial-gradient(ellipse 350px 120px at 60% 90%, var(--mountain-medium) 0%, transparent 55%),
        radial-gradient(ellipse 300px 100px at 85% 87%, var(--mountain-light) 0%, transparent 50%),
        /* 宣纸细腻肌理 */
        radial-gradient(circle at 20% 30%, rgba(180, 175, 170, 0.015) 0%, transparent 50%),
        radial-gradient(circle at 80% 70%, rgba(180, 175, 170, 0.01) 0%, transparent 50%);
    pointer-events: none;
    z-index: 0;
}

/* 右侧竹叶点缀 */
body::after {
    content: '';
    position: fixed;
    top: 0;
    right: 0;
    width: 180px;
    height: 100vh;
    background:
        radial-gradient(ellipse 45px 130px at 78% 15%, var(--bamboo-light) 0%, transparent 70%),
        radial-gradient(ellipse 40px 110px at 82% 40%, var(--bamboo-medium) 0%, transparent 70%),
        radial-gradient(ellipse 35px 100px at 79% 65%, var(--bamboo-light) 0%, transparent 70%);
    pointer-events: none;
    z-index: 0;
}
```

### 3. 导航栏调整

```css
.nav-wrapper {
    background: rgba(255, 253, 250, 0.88);
    backdrop-filter: blur(24px) saturate(120%);
    border-bottom: 1px solid rgba(180, 175, 170, 0.2);
    box-shadow: 0 2px 10px rgba(100, 95, 90, 0.02);
}

.nav-wrapper.scrolled {
    background: rgba(255, 253, 250, 0.95);
    box-shadow: 0 3px 16px rgba(100, 95, 90, 0.04);
}
```

### 4. 卡片组件调整

```css
.section {
    background: var(--card-bg);
    border: 1px solid var(--card-border);
    box-shadow: 0 6px 24px rgba(100, 95, 90, 0.03);
}

.section::before {
    /* 右侧淡墨装饰条 */
    background: linear-gradient(180deg, transparent 0%, rgba(100, 130, 115, 0.02) 100%);
}

.push-card {
    background: rgba(254, 252, 248, 0.75);
    border: 1px solid rgba(180, 175, 170, 0.2);
}

.push-card:hover {
    box-shadow: 0 8px 28px rgba(100, 95, 90, 0.05);
    border-color: rgba(160, 155, 150, 0.3);
}
```

### 5. 按钮调整

```css
.tool-button.primary {
    background: linear-gradient(135deg, var(--primary-light), var(--primary));
    box-shadow: 0 4px 16px rgba(100, 130, 115, 0.12);
}

.tool-button:hover {
    box-shadow: 0 6px 22px rgba(100, 130, 115, 0.18);
}
```

## 边界条件与异常处理

### 1. 响应式适配
- 在小屏幕（<768px）上，淡墨装饰元素应简化，避免影响内容阅读
- 竹子和远山装饰在移动端应降低不透明度

### 2. 可访问性保证
- 确保文字与背景对比度符合WCAG AA标准（最低4.5:1）
- 避免淡色背景导致文字难以辨认
- 保留所有原有的交互反馈效果

### 3. 浏览器兼容性
- backdrop-filter在不支持的浏览器中降级为纯色背景
- radial-gradient在不支持的浏览器中提供纯色fallback

## 数据流路径

无数据流变化，仅修改CSS样式层，不涉及JavaScript逻辑、API调用或数据传输。

## 预期结果

1. **视觉效果**：
   - 背景呈现米白色宣纸质感，整体淡雅清新
   - 左右两侧竹子剪影若隐若现，不遮挡中心内容
   - 远山轮廓朦胧，云雾缭绕感强
   - 所有色彩饱和度低，对比度柔和

2. **用户体验**：
   - 所有原有功能正常工作
   - 文字和按钮清晰可读
   - 保持流畅的交互体验
   - 营造禅意清雅的氛围感

3. **技术指标**：
   - CSS样式改造完成
   - JavaScript逻辑完全保留
   - 响应式布局正常工作
   - 性能无明显下降