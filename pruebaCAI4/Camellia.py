import camellia
import cryptography
from cryptography.fernet import Fernet
import serpent
from time import time
import random
from random import _urandom
from Crypto import Random
from CryptoPlus.Cipher import python_Serpent



def test_camellia_256_CBC(datos):
    key = _urandom(16)
    iv = Random.new().read(16)
    cipher = python_Serpent.new(key, python_Serpent.MODE_CBC, iv)
    tiempo_comienzo_cifrado = time()
    enc_datos = cipher.encrypt(datos)
    tiempo_cifrado = time() - tiempo_comienzo_cifrado
    msg = iv + enc_datos
    tiempo_comienzo_descifrado = time()
    cipher.decrypt(msg)
    tiempo_descifrado = time() - tiempo_comienzo_descifrado
    incremento_espacial = len(enc_datos) - len(datos)
    return tiempo_cifrado, tiempo_descifrado, incremento_espacial
