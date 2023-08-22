# ==============================IMPORT LIB======================================
# ======================IMPORT PYTHON BUILT-IN LIB==============================
import os
import subprocess
# ==========================IMPORT PIP3 LIB=====================================

# ==========================IMPORT USER LIB=====================================
m_acceptAuthors = ['Amrish Nair <amrish@bio-rithm.com>',
'Luong Thi Chuyen <chuyen@bio-rithm.com>',
'Nguyen Ba Hung <hung@bio-rithm.com>',
]

m_acceptAuthorsDiffName = {
    'Santhosh Kumar <santhosh@bio-rithm.com>' : 13
}


# lines = subprocess.check_output('git shortlog --summary --numbered --email', shell=True, text=True)
lines = open('/tmp/git_authors.txt', 'r').read()
print('lines = {}'.format(lines))
if (len(lines) == 0):
    assert False, "NOT FOUND ANY AUTHOR"

print('Current Authors = \n{}'.format(lines))

for line in lines.split('\n')[:-1]:
    author_name = (line.split('\t')[1])
    author_num_commit = int(line.split('\t')[0])

    if author_name in m_acceptAuthors:
        continue
    if author_name in m_acceptAuthorsDiffName.keys():
        if author_num_commit > m_acceptAuthorsDiffName[author_name]:
            assert False, "Wrong Author detected"
        else:
            continue

    assert False, "Wrong Author detected"

print('Done Check Authors')

