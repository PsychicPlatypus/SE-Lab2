package com.laboratory.lab2_se.domain

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log


fun speechRecognitionService(speechRecognizer: SpeechRecognizer?) {
    val houseStatesWriteService = HouseStatesWriteService()
    when (speechRecognizer) {
        null -> {
            Log.d("SpeechRecognizer", "Speech recognition is not available")
            return
        }

        else -> {
            speechRecognizer.setRecognitionListener(object : RecognitionListener {
                override fun onReadyForSpeech(params: Bundle?) {
                    Log.d("SpeechRecognizer", "Speech recognition is ready")
                }

                override fun onBeginningOfSpeech() {
                    Log.d("SpeechRecognizer", "Speech recognition is ready")
                }

                override fun onRmsChanged(rmsdB: Float) {
                    Log.d("SpeechRecognizer", "Speech recognition is ready")
                }

                override fun onBufferReceived(buffer: ByteArray?) {
                    Log.d("SpeechRecognizer", "Speech recognition is ready")
                }

                override fun onEndOfSpeech() {
                    Log.d("SpeechRecognizer", "Speech recognition is ready")
                }

                override fun onError(error: Int) {
                    Log.d("SpeechRecognizer", "Speech recognition is error: $error")
                }

                //...

                override fun onResults(results: Bundle) {
                    val data: ArrayList<String>? =
                        results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    Log.e("SpeechRecognizer", "onResults: $data")
                    when (data?.get(0)?.lowercase()) {
                        "open door" -> {
                            Log.d("SpeechRecognizer", "Open door")
                            houseStatesWriteService.writeDoorState("open")
                        }

                        "close door" -> {
                            Log.d("SpeechRecognizer", "Close door")
                            houseStatesWriteService.writeDoorState("closed")
                        }

                        "open window" -> {
                            Log.d("SpeechRecognizer", "Open window")
                            houseStatesWriteService.writeWindowState("open")
                        }

                        "close window" -> {
                            Log.d("SpeechRecognizer", "Close window")
                            houseStatesWriteService.writeWindowState("closed")
                        }

                        "turn on light" -> {
                            Log.d("SpeechRecognizer", "Turn on light")
                            houseStatesWriteService.writeLightState("on")
                        }

                        "turn off light" -> {
                            Log.d("SpeechRecognizer", "Turn off light")
                            houseStatesWriteService.writeLightState("off")
                        }

                        else -> {
                            Log.d("SpeechRecognizer", "Unknown command")
                        }
                    }
                }

                override fun onPartialResults(partialResults: Bundle?) {
                    Log.d("SpeechRecognizer", "Speech recognition is ready")
                }

                override fun onEvent(eventType: Int, params: Bundle?) {
                    Log.d("SpeechRecognizer", "Speech recognition is ready")
                }

                //...
            })
        }
    }

    val recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
        putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
    }

    speechRecognizer.startListening(recognizerIntent)
}