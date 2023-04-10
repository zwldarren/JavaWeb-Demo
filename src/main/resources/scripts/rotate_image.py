import sys
from PIL import Image

file_path = sys.argv[1]
angle = float(sys.argv[2])

image = Image.open(file_path)

rotated_image = image.rotate(angle, expand=True)

rotated_image.save(file_path)
