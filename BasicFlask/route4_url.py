from flask import Flask, render_template, request, redirect, url_for

app = Flask(__name__)

@app.route('/', defaults={'mesg' : ''})       # when called without passing any values, the default would be an empty string
@app.route('/<string:mesg>', methods=['PUT'])                # default method is GET
def init(mesg):                             # this is a comment. You can create your own function name
    return '<h1>Welcome to Flask! </h1> {} '.format(mesg)

@app.route('/about', methods=['GET','POST','PUT'])
def your_url():
    if request.method == 'POST':
        return render_template('about.html', name=request.form.get('dname', 'No data passed'))
        # request.form.get if method=POST
        # request.args.get if method=GET
    elif request.method == 'GET' :
        return redirect('/')   # calling init through the route '/' without passing any value
    else:
        return redirect(url_for('init',mesg='Sorry, it is not yet implemented'))  # calling init


if __name__ == '__main__':
    app.run(host='0.0.0.0',debug=True)   # host='0.0.0.0' means whatever your public ip assigned will be used

# to test: use RestMan extensions in chrome