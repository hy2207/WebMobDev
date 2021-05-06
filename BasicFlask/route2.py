from flask import Flask, render_template,  request, escape

app = Flask(__name__)


@app.route('/')
def init():                            # this is a comment. You can create your own function name
    return '<h1> {} </h1>'.format(__name__)

@app.route('/about')
def home():
    return render_template('about.html',  name='Special Agent 080')  # the html file should be in templates (folder)

@app.route('/passing')                  # http://3.237.92.38:5000/passing
def show_code():
    code = request.args.get('dcode','no value given')
    return code
    
# info about escape : https://tedboy.github.io/flask/generated/flask.escape.html

@app.route('/user/<username>')          # http://3.237.92.38:5000/user/Jake or http://3.237.92.38:5000/user/Whatever%20Name
def show_user(username):
    # show the user profile for that user
    return 'User %s' % escape(username)

@app.route('/post/<int:post_id>')       # http://3.237.92.38:5000/post/145
def show_post(post_id):                 # id is an integer
    return 'Post %d' % post_id

@app.route('/path/<path:subpath>')      # http://3.237.92.38:5000/path/folderA/folderB
def show_subpath(subpath):
    # show the subpath after /path/
    return 'Subpath %s' % escape(subpath)
    
if __name__ == '__main__':
    app.run(host='0.0.0.0',debug=True)
