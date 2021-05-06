from flask import Flask, render_template, request, redirect, url_for, jsonify, flash
import json
import os
from werkzeug.utils import secure_filename


UPLOAD_FOLDER = './datatemp'
ALLOWED_EXTENSIONS = {'txt', 'pdf', 'png', 'jpg', 'jpeg', 'gif'}

app = Flask(__name__)
app.secret_key = 'someSecretKey123'
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER


@app.route('/')       
def init():                            
    return render_template('uploadPage.html')


@app.route('/processUpload', methods=['POST'])
def processingUpload():
    if request.method == 'POST':
        # check if the post request has the file part
        if 'file' not in request.files:
            flash('No file part')
            return redirect(url_for('init'))
        
        file = request.files['file']
        # if user does not select file, browser also
        # submit an empty part without filename
        
        if file.filename == '':
            flash('No selected file')
            return redirect(url_for('init'))
        
        if file and allowed_file(file.filename):
            filename = secure_filename(file.filename)
            file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))
            return filename + " saved "


# split : splits string into a list ... (delimiter, maxsplit)
def allowed_file(filename):
    print (filename.split('.', 1))    
    return '.' in filename and \
           filename.split('.', 1)[1].lower() in ALLOWED_EXTENSIONS  

if __name__ == '__main__':
    app.run(host='0.0.0.0',debug=True)   # host='0.0.0.0' means whatever your public ip assigned will be used

# to test: use Rested extensions in chrome