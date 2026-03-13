# HeadRender

[![Minecraft](https://img.shields.io/badge/Minecraft-1.7--1.21.11-dark_green.svg)](https://shields.io/)
[![Version](https://img.shields.io/badge/version-0.1.1-blue.svg)](https://github.com/senkex/HeadRender)
[![JitPack](https://jitpack.io/v/senkex/HeadRender.svg)](https://jitpack.io/#senkex/HeadRender)

HeadRender is a lightweight library that renders **Minecraft player heads directly in chat** using colored characters.

The library downloads player skins, converts them into pixel data and renders them using chat color codes.

It supports **HEX colors (1.16+)** and automatically falls back to **legacy colors for older versions**.


## Features

• Render player heads directly in chat  
• HEX RGB color support  
• Legacy ChatColor fallback  
• Skin caching system  
• Pixel scaling utilities  
• Simple developer API  
• Compatible with Bukkit / Spigot / Paper  
• Compatible with Minecraft **1.7 → 1.21.11**

## Supported Versions

HeadRender works on all major Bukkit based servers.

```
Minecraft 1.7
Minecraft 1.8
Minecraft 1.9
Minecraft 1.10
Minecraft 1.11
Minecraft 1.12
Minecraft 1.13
Minecraft 1.14
Minecraft 1.15
Minecraft 1.16
Minecraft 1.17
Minecraft 1.18
Minecraft 1.19
Minecraft 1.20
Minecraft 1.21
Minecraft 1.21.11
```

---

## Skin Provider

HeadRender fetches skins from:

```
https://minotar.net
```

Endpoints used internally:

```
/avatar
/helm
/skin
```

---

# Installation

The library is distributed using **JitPack**.

Repository:

```
https://jitpack.io
```

---

## Maven

Add the repository:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Add the dependency:

```xml
<dependency>
    <groupId>com.github.senkex</groupId>
    <artifactId>HeadRender</artifactId>
    <version>0.1.1</version>
</dependency>
```

---

## Gradle

```kotlin
repositories {
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.senkex:HeadRender:0.1.1")
}
```

---

> [!IMPORTANT]
> If your plugin supports Minecraft **1.13+**, it is recommended to add  
> `api-version: "1.13"` in your **plugin.yml**.

Example:

```yml
api-version: "1.13"
```

This does **not break compatibility with older versions**.

---

> [!CAUTION]
> If multiple plugins use HeadRender it is recommended to **shade the library**
> to avoid potential version conflicts.

---

# Getting Started

The main entry point of the library is:

```
HeadRenderAPI
```

The API provides simple methods for rendering player heads in chat.

---

# Basic Usage

Render the player's own head:

```java
HeadRenderAPI.renderHead(player);
```

---

Render another player's head:

```java
HeadRenderAPI.renderHead(player, "Notch");
```

---

Render with custom options:

```java
RenderOptions options = new RenderOptions(
        8,
        "█",
        true,
        true
);

HeadRenderAPI.renderHead(player, "Dinnerbone", options);
```

---

# RenderOptions

RenderOptions controls how the head is rendered.

Constructor:

```java
RenderOptions(int size, String character, boolean hexColors, boolean helmetLayer)
```

Parameters:

| Parameter | Description |
|--------|-------------|
| size | Render resolution |
| character | Character used as pixel |
| hexColors | Enables RGB colors |
| helmetLayer | Includes helmet / hat layer |

Example:

```java
RenderOptions options = new RenderOptions(
        8,
        "█",
        true,
        true
);
```

---

Default configuration:

```java
RenderOptions.defaults();
```

Default values:

```
size = 8
character = █
hexColors = true
helmetLayer = true
```

---

# AsyncUtil

The library includes a scheduler utility for asynchronous tasks.

Before using it you must initialize it in your plugin.

---

## Initialization

Call this in your plugin **onEnable()**.

```java
@Override
public void onEnable() {
    AsyncUtil.init(this);
}
```

---

## Running async tasks

```java
AsyncUtil.runAsync(() -> {

});
```

---

## Running sync tasks

```java
AsyncUtil.runSync(() -> {

});
```

---

# Skin Cache

HeadRender includes a simple in-memory skin cache.

Purpose:

• Prevent repeated HTTP requests  
• Improve rendering performance  

Default configuration:

```
Cache TTL: 10 minutes
Storage: ConcurrentHashMap
Thread safe: yes
```

Cache utilities:

```java
SkinCache.clear();
SkinCache.size();
```

---

# Image Scaling

Image scaling is handled by **ImageScaler**.

Two scaling algorithms are provided.

Smooth scaling:

```java
ImageScaler.scaleSmooth(image, width, height);
```

Pixel scaling (recommended for skins):

```java
ImageScaler.scalePixel(image, width, height);
```

---

# Color Conversion

ColorUtil converts RGB values into Minecraft chat colors.

HEX format:

```
§x§R§R§G§G§B§B
```

Example:

```java
ColorUtil.toHex(color);
```

Legacy fallback:

```java
ColorUtil.toLegacy(color);
```

---

# Example Command

Example Bukkit command implementation.

```java
public class HeadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            HeadRenderAPI.renderHead(player);
            return true;
        }

        HeadRenderAPI.renderHead(player, args[0]);

        return true;
    }
}
```

---

# Building

Clone the repository:

```
git clone https://github.com/senkex/HeadRender.git
```

Build with Maven:

```
mvn clean package
```

---

# Contributing

Contributions are welcome.

Guidelines:

• Ensure the code compiles  
• Test across multiple Minecraft versions  
• Follow existing code style  

Open a Pull Request if you want to contribute improvements.

---

# License

MIT License

Copyright (c) 2026 Senkex

Permission is hereby granted to use, modify and distribute this software.

---

# Links

Repository

```
https://github.com/senkex/HeadRender
```

JitPack

```
https://jitpack.io/#senkex/HeadRender
```

Issues

```
https://github.com/senkex/HeadRender/issues
```
