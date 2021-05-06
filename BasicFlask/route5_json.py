from flask import Flask, render_template, request, redirect, url_for, jsonify
import json
import os



app = Flask(__name__)
app.secret_key = 'someSecretKey123'

@app.route('/', defaults={'mesg' : ''})       # when called without passing any values, the default would be an empty string
@app.route('/<string:mesg>', methods=['PUT'])                # default method is GET
def init(mesg):                             # this is a comment. You can create your own function name
    return '<h1>Welcome to Flask! </h1> {} '.format(mesg)

# put vs post : https://www.keycdn.com/support/put-vs-post

@app.route('/processjson', methods=['GET','POST','PUT'])
def proccessingJson():
    if request.method == 'POST':
        data = request.get_json();
        return render_template('jsonTable.html', n=len(data), theData=data)
        # request.form.get if method=POST
        # request.args.get if method=GET
    elif request.method == 'GET' :
        if os.path.exists('./static/mock_data.json'):
            with open('./static/mock_data.json') as theFile:
               data = json.load(theFile)
            return render_template('jsonTable.html', n=len(data), theData=data)
    else:
        data = request.get_json();
        with open('./datatemp/dummy.json','w') as dummyfile:
            json.dump(data, dummyfile)
        return 'saved'
    

if __name__ == '__main__':
    app.run(host='0.0.0.0',debug=True)   # host='0.0.0.0' means whatever your public ip assigned will be used

# to test: use Rested extensions in chrome