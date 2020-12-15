package com.example.myapplication.business.arch.creator

import com.example.myapplication.business.arch.models.read.ReadableModel
import com.example.myapplication.foundation.arch.creator.Creator

interface ReadableModelCreator<T>: Creator<T, T> where T: ReadableModel
